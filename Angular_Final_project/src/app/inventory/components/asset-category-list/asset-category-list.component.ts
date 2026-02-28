import { Component, OnInit } from '@angular/core';
import { AssetCategory } from '../../models/asset-category.model';
import { AssetCategoryService } from '../../services/asset-category.service';

@Component({
  selector: 'app-asset-category-list',
  templateUrl: './asset-category-list.component.html',
  styleUrls: ['./asset-category-list.component.scss']
})
export class AssetCategoryListComponent implements OnInit {

  categories: AssetCategory[] = [];
  loading = true;

  constructor(private categoryService: AssetCategoryService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories() {
    this.loading = true;
    this.categoryService.getAll().subscribe({
      next: (res) => {
        this.categories = res;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  deleteCategory(id: number) {
    if (confirm('Delete this category?')) {
      this.categoryService.delete(id).subscribe(() => {
        this.loadCategories();
      });
    }
  }
}
