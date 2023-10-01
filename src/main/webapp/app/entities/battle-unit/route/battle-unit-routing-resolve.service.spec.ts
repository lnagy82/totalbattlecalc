jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IBattleUnit, BattleUnit } from '../battle-unit.model';
import { BattleUnitService } from '../service/battle-unit.service';

import { BattleUnitRoutingResolveService } from './battle-unit-routing-resolve.service';

describe('BattleUnit routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: BattleUnitRoutingResolveService;
  let service: BattleUnitService;
  let resultBattleUnit: IBattleUnit | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Router, ActivatedRouteSnapshot],
    });
    mockRouter = TestBed.inject(Router);
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
    routingResolveService = TestBed.inject(BattleUnitRoutingResolveService);
    service = TestBed.inject(BattleUnitService);
    resultBattleUnit = undefined;
  });

  describe('resolve', () => {
    it('should return IBattleUnit returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBattleUnit = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBattleUnit).toEqual({ id: 123 });
    });

    it('should return new IBattleUnit if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBattleUnit = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultBattleUnit).toEqual(new BattleUnit());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as BattleUnit })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBattleUnit = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBattleUnit).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
