jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IUnit, Unit } from '../unit.model';
import { UnitService } from '../service/unit.service';

import { UnitRoutingResolveService } from './unit-routing-resolve.service';

describe('Unit routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: UnitRoutingResolveService;
  let service: UnitService;
  let resultUnit: IUnit | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Router, ActivatedRouteSnapshot],
    });
    mockRouter = TestBed.inject(Router);
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
    routingResolveService = TestBed.inject(UnitRoutingResolveService);
    service = TestBed.inject(UnitService);
    resultUnit = undefined;
  });

  describe('resolve', () => {
    it('should return IUnit returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultUnit = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultUnit).toEqual({ id: 123 });
    });

    it('should return new IUnit if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultUnit = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultUnit).toEqual(new Unit());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as Unit })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultUnit = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultUnit).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
