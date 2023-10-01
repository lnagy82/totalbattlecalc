import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UnitDetailComponent } from './unit-detail.component';

describe('Component Tests', () => {
  describe('Unit Management Detail Component', () => {
    let comp: UnitDetailComponent;
    let fixture: ComponentFixture<UnitDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [UnitDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ unit: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(UnitDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UnitDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load unit on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.unit).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
  });
});
