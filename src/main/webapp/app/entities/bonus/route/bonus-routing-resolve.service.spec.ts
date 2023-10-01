jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IBonus, Bonus } from '../bonus.model';
import { BonusService } from '../service/bonus.service';

import { BonusRoutingResolveService } from './bonus-routing-resolve.service';

describe('Bonus routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: BonusRoutingResolveService;
  let service: BonusService;
  let resultBonus: IBonus | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Router, ActivatedRouteSnapshot],
    });
    mockRouter = TestBed.inject(Router);
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
    routingResolveService = TestBed.inject(BonusRoutingResolveService);
    service = TestBed.inject(BonusService);
    resultBonus = undefined;
  });

  describe('resolve', () => {
    it('should return IBonus returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBonus = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBonus).toEqual({ id: 123 });
    });

    it('should return new IBonus if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBonus = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultBonus).toEqual(new Bonus());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as Bonus })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBonus = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBonus).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
