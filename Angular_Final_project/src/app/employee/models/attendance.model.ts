// attendance.model.ts
export enum ShiftType {
  MORNING = 'MORNING',
  EVENING = 'EVENING',
  NIGHT = 'NIGHT'
}

export interface Attendance {
  id: number;
  employeeId: number;
  date: string;       
  checkIn?: string;   
  checkOut?: string;  
  shift: ShiftType;
  name: string;
}
