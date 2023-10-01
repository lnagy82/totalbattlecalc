jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IFeature, Feature } from '../feature.model';
import { FeatureService } from '../service/feature.service';

import { FeatureRoutingResolveService } from './feature-routing-resolve.service';

describe('Feature routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: FeatureRoutingResolveService;
  let service: FeatureService;
  let resultFeature: IFeature | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Router, ActivatedRouteSnapshot],
    });
    mockRouter = TestBed.inject(Router);
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
    routingResolveService = TestBed.inject(FeatureRoutingResolveService);
    service = TestBed.inject(FeatureService);
    resultFeature = undefined;
  });

  describe('resolve', () => {
    it('should return IFeature returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFeature = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFeature).toEqual({ id: 123 });
    });

    it('should return new IFeature if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFeature = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultFeature).toEqual(new Feature());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as Feature })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFeature = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultFeature).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
