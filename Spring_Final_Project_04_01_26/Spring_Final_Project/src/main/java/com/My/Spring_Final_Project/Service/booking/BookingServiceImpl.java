package com.My.Spring_Final_Project.Service.booking;

import com.My.Spring_Final_Project.DTO.booking.*;
import com.My.Spring_Final_Project.Entity.auth.User;
import com.My.Spring_Final_Project.Entity.customer.Customer;
import com.My.Spring_Final_Project.Entity.booking.Booking;
import com.My.Spring_Final_Project.Entity.booking.BookingItem;
import com.My.Spring_Final_Project.Entity.room.Room;
import com.My.Spring_Final_Project.Entity.room.RoomType;
import com.My.Spring_Final_Project.Enum.booking.BookingStatus;
import com.My.Spring_Final_Project.Repository.booking.BookingItemRepository;
import com.My.Spring_Final_Project.Repository.booking.BookingRepository;
import com.My.Spring_Final_Project.Repository.auth.UserRepository;
import com.My.Spring_Final_Project.Repository.customer.CustomerRepository;
import com.My.Spring_Final_Project.Repository.room.RoomRepository;
import com.My.Spring_Final_Project.Repository.room.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingItemRepository bookingItemRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final RoomTypeRepository roomTypeRepository;


    // ================= CREATE BOOKING =================

    public BookingResponseDTO createBooking(BookingRequestDTO request) {

        if (request.getRoomId() == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
        if (request.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
//        User user = authUtil.getCurrentUser(); // JWT থেকে
//        booking.setCustomer(user);


        Booking booking = new Booking();

// Ensure unique booking code
        String bookingCode;
        do {
            bookingCode = "BK-" + UUID.randomUUID().toString().substring(0, 8);
        } while (bookingRepository.existsByBookingCode(bookingCode));

        booking.setBookingCode(bookingCode);
        booking.setRoom(room);
        booking.setCustomer(customer);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setStatus(BookingStatus.PENDING);
//        booking.setCreatedBy(user);

// ✅ RoomType conversion: String -> RoomType entity
        if (request.getRoomType() != null && !request.getRoomType().isEmpty()) {
//            String typeName = request.getRoomType().toUpperCase();

            RoomType rt = roomTypeRepository.findByName(request.getRoomType())
                    .orElseThrow(() -> new RuntimeException("Invalid room type: " + request.getRoomType()));
            booking.setRoomType(rt);
        }

        booking.setAdults(request.getAdults());
        booking.setChildren(request.getChildren());



        // --- Calculate total amount ---
        BigDecimal totalAmount = BigDecimal.ZERO;

        // Room cost calculation (safe check for pricePerNight)
        long nights = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        if (room.getPricePerNight() != null) {
            BigDecimal roomTotal = room.getPricePerNight().multiply(BigDecimal.valueOf(nights));
            totalAmount = totalAmount.add(roomTotal);
        }

        // Booking items cost
        if (request.getItems() != null) {
            for (BookingItemRequestDTO i : request.getItems()) {
                if (i.getPrice() != null && i.getQuantity() != null) {
                    BigDecimal itemTotal = i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity()));
                    totalAmount = totalAmount.add(itemTotal);
                }
            }
        }

        booking.setTotalAmount(totalAmount); // <-- important

        bookingRepository.save(booking);

        // Save Booking Items (optimized)
//        List<BookingItem> items = new ArrayList<>();
//        if (request.getItems() != null) {
//            items = request.getItems().stream().map(i -> {
//                BookingItem item = new BookingItem();
//                item.setBooking(booking);
//                item.setItemName(i.getItemName());
//                item.setQuantity(i.getQuantity());
//                item.setPrice(
//                        item.getPrice() != null ? item.getPrice() : BigDecimal.ZERO
//                );
//
//
//                item.setDescription(i.getDescription());
//                item.setCategory(i.getCategory());
//                item.setIsActive(true);
//                return item;
//            }).toList();
//
//            bookingItemRepository.saveAll(items);
//        }


        List<BookingItem> items = new ArrayList<>();

        if (request.getItems() != null) {

            items = request.getItems().stream().map(i -> {

                BookingItem item = new BookingItem();
                item.setBooking(booking);

                item.setItemName(i.getItemName());
                item.setQuantity(i.getQuantity());

                // 🔥 DEMO PRICE (NO NULL, NO ERROR)
                BigDecimal demoPrice;

                switch (i.getCategory()) {
                    case "ROOM":
                        demoPrice = new BigDecimal("3500.00");
                        break;


                    case "SPA":
                        demoPrice = new BigDecimal("3500.00");
                        break;


                    case "SWIMMING_POOL_ACCESS":
                        demoPrice = new BigDecimal("3500.00");
                        break;

                    case "FOOD":
                        demoPrice = new BigDecimal("500.00");
                        break;

                    case "SERVICE":
                        demoPrice = new BigDecimal("800.00");
                        break;

                    case "EXTRA":
                        demoPrice = new BigDecimal("300.00");
                        break;

                    default:
                        demoPrice = BigDecimal.ZERO;
                }

                item.setPrice(demoPrice);

                item.setDescription(i.getDescription());
                item.setCategory(i.getCategory());
                item.setIsActive(true);

                return item;

            }).toList();

            bookingItemRepository.saveAll(items);
        }

        return mapToResponse(booking, items);
    }

    // ================= GET BOOKING BY CODE =================
    @Override
    public BookingResponseDTO getBookingByCode(String bookingCode) {
        Booking booking = bookingRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        List<BookingItem> items = bookingItemRepository.findByBooking(booking);

        return mapToResponse(booking, items);
    }

    // ================= GET ALL BOOKINGS =================
    @Override
    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(b -> mapToResponse(b, bookingItemRepository.findByBooking(b)))
                .collect(Collectors.toList());
    }


    // ================= UPDATE BOOKING =================
    @Override
    public BookingResponseDTO updateBooking(String bookingCode, BookingRequestDTO request) {

        Booking booking = bookingRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // ❗ Business Rule Protection
        if (booking.getStatus() == BookingStatus.CONFIRMED
                || booking.getStatus() == BookingStatus.COMPLETED) {
            throw new IllegalStateException("Confirmed or completed booking cannot be modified");
        }

        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        bookingRepository.save(booking);

        // Soft delete old items
        List<BookingItem> oldItems = bookingItemRepository.findByBooking(booking);
        oldItems.forEach(i -> i.setIsActive(false));
        bookingItemRepository.saveAll(oldItems);

        // Save new items
        List<BookingItem> newItems = request.getItems().stream().map(i -> {
            BookingItem item = new BookingItem();
            item.setBooking(booking);
            item.setItemName(i.getItemName());
            item.setQuantity(i.getQuantity());
            item.setIsActive(true);
            return item;
        }).toList();

        bookingItemRepository.saveAll(newItems);

        return mapToResponse(booking, newItems);
    }

    // ================= CANCEL BOOKING =================
    @Override
    public void cancelBooking(String bookingCode) {

        Booking booking = bookingRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        List<BookingItem> items = bookingItemRepository.findByBooking(booking);
        items.forEach(i -> i.setIsActive(false));
        bookingItemRepository.saveAll(items);
    }

    // ================= BOOKING STATS (ADMIN) =================
    @Override
    public BookingStatsDTO getBookingStats() {

        return BookingStatsDTO.builder()
                .totalBookings(bookingRepository.count())
                .pendingBookings(bookingRepository.countByStatus(BookingStatus.PENDING))
                .confirmedBookings(bookingRepository.countByStatus(BookingStatus.CONFIRMED))
                .cancelledBookings(bookingRepository.countByStatus(BookingStatus.CANCELLED))
                .completedBookings(bookingRepository.countByStatus(BookingStatus.COMPLETED))
                .build();
    }

    // ================= MY BOOKING STATS (USER) =================
    @Override
    public BookingStatsDTO getMyBookingStats() {

        String userName = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return BookingStatsDTO.builder()
                .totalBookings(bookingRepository.countByUser_UserName(userName))
                .pendingBookings(
                        bookingRepository.countByUser_UserNameAndStatus(userName, BookingStatus.PENDING))
                .confirmedBookings(
                        bookingRepository.countByUser_UserNameAndStatus(userName, BookingStatus.CONFIRMED))
                .cancelledBookings(
                        bookingRepository.countByUser_UserNameAndStatus(userName, BookingStatus.CANCELLED))
                .build();
    }

    // ================= HELPER MAPPER =================
    private BookingResponseDTO mapToResponse(Booking booking, List<BookingItem> items) {

        List<BookingItemResponseDTO> itemDTOs = items.stream()
                .filter(BookingItem::getIsActive)
                .map(i -> new BookingItemResponseDTO(
                        i.getItemName(),
                        i.getQuantity(),
                        i.getPrice(),           // add price
                        i.getDescription(),     // add description
                        i.getCategory()         // add category
                ))
                .toList();
        int nights = 0;
        if (booking.getCheckInDate() != null && booking.getCheckOutDate() != null) {
            nights = (int) ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());
        }

        String roomTypeName = null;
        if (booking.getRoom() != null && booking.getRoom().getRoomType() != null) {
            roomTypeName = booking.getRoom().getRoomType().getName(); // <-- entity theke name nibo
        }

        return new BookingResponseDTO(
                booking.getId(),
                booking.getBookingCode(),
                booking.getCustomer() != null ? booking.getCustomer().getId() : null,
                booking.getCustomer() != null ? booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName() : null,
                booking.getRoom() != null ? booking.getRoom().getId() : null,
                booking.getRoom() != null ? booking.getRoom().getRoomNumber() : null,
                booking.getCheckInDate() != null ? booking.getCheckInDate().toString() : null,
                booking.getCheckOutDate() != null ? booking.getCheckOutDate().toString() : null,
                booking.getStatus() != null ? booking.getStatus().name() : null,
                itemDTOs,
                booking.getAdults(),
                booking.getChildren(),
                nights,
                roomTypeName
        );
    }
}


