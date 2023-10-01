import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BattleDetailComponent } from './battle-detail.component';

describe('Component Tests', () => {
  describe('Battle Management Detail Component', () => {
    let comp: BattleDetailComponent;
    let fixture: ComponentFixture<BattleDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [BattleDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ battle: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(BattleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BattleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load battle on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.battle).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
  });
});
