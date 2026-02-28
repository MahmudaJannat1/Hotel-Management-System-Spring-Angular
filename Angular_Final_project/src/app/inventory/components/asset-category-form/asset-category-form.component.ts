import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AssetCategoryService } from '../../services/asset-category.service';

@Component({
  selector: 'app-asset-category-form',
  templateUrl: './asset-category-form.component.html',
  styleUrls: ['./asset-category-form.component.scss']
})
export class AssetCategoryFormComponent implements OnInit {

  id?: number;
  model = { name: '', description: 'abc' };
  loading = false;

  constructor(
    private service: AssetCategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.service.get(this.id).subscribe(res => {
        this.model = res;
      });
    }
  }

  submit() {
    this.loading = true;

    const action = this.id
      ? this.service.update(this.id, this.model)
      : this.service.create(this.model);

    action.subscribe(() => {
      this.router.navigate(['/inventory/asset/list']);
    });
  }
}
