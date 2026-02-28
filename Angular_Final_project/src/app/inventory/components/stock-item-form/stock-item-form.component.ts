import { Component, OnInit } from '@angular/core';
import { AssetCategory, StockItem } from '../../models/stock-item.model';
import { StockItemService } from '../../services/stock-item.service';
import { AssetCategoryService } from '../../services/asset-category.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-stock-item-form',
  templateUrl: './stock-item-form.component.html',
  styleUrls: ['./stock-item-form.component.scss']
})
export class StockItemFormComponent implements OnInit {

  model: StockItem = {
    id: 0,
    name: '',
    quantity: 0,
    minQuantity: 0,
    categoryId: 0,
    categoryName:''
  };

  categories: AssetCategory[] = [];
  loading = false;
  id?: number;

  constructor(
    private stockService: StockItemService,
    private categoryService: AssetCategoryService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loadCategories();

    if(this.id) {
      this.stockService.getById(this.id).subscribe(res => this.model = res);
    }
  }

  loadCategories() {
    this.categoryService.getAll().subscribe(res => this.categories = res);
  }

submit() {
  this.loading = true;

  // map frontend model to backend DTO
  const payload = {
    ...this.model,
    categoryId: this.model.categoryId // backend expects 'categoryId'
  };

  if(this.id) {
    this.stockService.update(this.id, payload)
      .subscribe(() => this.router.navigate(['/inventory/stock/list']));
  } else {
    this.stockService.create(payload)
      .subscribe(() => this.router.navigate(['/inventory/stock/list']));
  }
}


}
