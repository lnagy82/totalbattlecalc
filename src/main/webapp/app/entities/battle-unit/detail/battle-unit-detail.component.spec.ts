import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BattleUnitDetailComponent } from './battle-unit-detail.component';

describe('Component Tests', () => {
  describe('BattleUnit Management Detail Component', () => {
    let comp: BattleUnitDetailComponent;
    let fixture: ComponentFixture<BattleUnitDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [BattleUnitDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ battleUnit: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(BattleUnitDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BattleUnitDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load battleUnit on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.battleUnit).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
  });
});
