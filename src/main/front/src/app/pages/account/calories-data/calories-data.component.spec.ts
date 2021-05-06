import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaloriesDataComponent } from './calories-data.component';

describe('CaloriesDataComponent', () => {
  let component: CaloriesDataComponent;
  let fixture: ComponentFixture<CaloriesDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaloriesDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaloriesDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
