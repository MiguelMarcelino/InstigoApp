import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackReadComponent } from './feedback-read.component';

describe('FeedbackReadComponent', () => {
  let component: FeedbackReadComponent;
  let fixture: ComponentFixture<FeedbackReadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FeedbackReadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedbackReadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
