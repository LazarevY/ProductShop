import { TestBed } from '@angular/core/testing';

import { OrderPayService } from './order-pay.service';

describe('OrderPayService', () => {
  let service: OrderPayService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderPayService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
