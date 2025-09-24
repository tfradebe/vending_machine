import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Dispense } from './dispense';

describe('Dispense', () => {
  let component: Dispense;
  let fixture: ComponentFixture<Dispense>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Dispense]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Dispense);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
