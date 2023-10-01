import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BonusDetailComponent } from './bonus-detail.component';

describe('Component Tests', () => {
  describe('Bonus Management Detail Component', () => {
    let comp: BonusDetailComponent;
    let fixture: ComponentFixture<BonusDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [BonusDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ bonus: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(BonusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BonusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bonus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bonus).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
  });
});
