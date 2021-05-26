import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductMiniCardComponent } from './product-mini-card.component';

describe('ProductMiniCardComponent', () => {
  let component: ProductMiniCardComponent;
  let fixture: ComponentFixture<ProductMiniCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductMiniCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductMiniCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
