import { TestBed } from '@angular/core/testing';

import { WalletserviceService } from './walletservice.service';

describe('WalletserviceService', () => {
  let service: WalletserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WalletserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
