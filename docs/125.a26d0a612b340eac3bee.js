"use strict";(self.webpackChunktotalbattlecalc=self.webpackChunktotalbattlecalc||[]).push([[125],{4991:(C,Z,i)=>{i.d(Z,{gK:()=>n,aW:()=>p,jo:()=>l,_l:()=>U});const n=20,p="asc",l="desc",U="sort"},7354:(C,Z,i)=>{i.d(Z,{b:()=>p});var n=i(1887);const p=l=>{let U=new n.LE;return l&&(Object.keys(l).forEach(s=>{"sort"!==s&&(U=U.set(s,l[s]))}),l.sort&&l.sort.forEach(s=>{U=U.append("sort",s)})),U}},7575:(C,Z,i)=>{function n(l){return null!=l}i.d(Z,{E:()=>n,z:()=>p});const p=l=>isNaN(l)?0:l},415:(C,Z,i)=>{i.r(Z),i.d(Z,{FeatureModule:()=>re});var n=i(1382),p=i(805),l=i(9375),U=i(6905),s=i(4991),e=i(2741),d=i(3348),g=i(2444),T=i(6274),r=i(3324),m=i(4243),c=i(6662),f=i(4477);const y=function(a){return{id:a}};function F(a,_){if(1&a){const t=e.EpF();e.TgZ(0,"form",1),e.NdJ("ngSubmit",function(){e.CHM(t);const u=e.oxw();return u.confirmDelete(u.feature.id)}),e._uU(1,"\n  "),e.TgZ(2,"div",2),e._uU(3,"\n    "),e.TgZ(4,"h4",3),e._uU(5,"Confirm delete operation"),e.qZA(),e._uU(6,"\n\n    "),e.TgZ(7,"button",4),e.NdJ("click",function(){return e.CHM(t),e.oxw().cancel()}),e._uU(8,"\xd7"),e.qZA(),e._uU(9,"\n  "),e.qZA(),e._uU(10,"\n\n  "),e.TgZ(11,"div",5),e._uU(12,"\n    "),e._UZ(13,"jhi-alert-error"),e._uU(14,"\n\n    "),e.TgZ(15,"p",6),e._uU(16,"\n      Are you sure you want to delete this Feature?\n    "),e.qZA(),e._uU(17,"\n  "),e.qZA(),e._uU(18,"\n\n  "),e.TgZ(19,"div",7),e._uU(20,"\n    "),e.TgZ(21,"button",8),e.NdJ("click",function(){return e.CHM(t),e.oxw().cancel()}),e._uU(22,"\n      "),e._UZ(23,"fa-icon",9),e._uU(24,"\xa0"),e.TgZ(25,"span",10),e._uU(26,"Cancel"),e.qZA(),e._uU(27,"\n    "),e.qZA(),e._uU(28,"\n\n    "),e.TgZ(29,"button",11),e._uU(30,"\n      "),e._UZ(31,"fa-icon",12),e._uU(32,"\xa0"),e.TgZ(33,"span",13),e._uU(34,"Delete"),e.qZA(),e._uU(35,"\n    "),e.qZA(),e._uU(36,"\n  "),e.qZA(),e._uU(37,"\n"),e.qZA()}if(2&a){const t=e.oxw();e.xp6(15),e.Q6J("translateValues",e.VKq(1,y,t.feature.id))}}let b=(()=>{class a{constructor(t,o){this.featureService=t,this.activeModal=o}cancel(){this.activeModal.dismiss()}confirmDelete(t){this.featureService.delete(t).subscribe(()=>{this.activeModal.close("deleted")})}}return a.\u0275fac=function(t){return new(t||a)(e.Y36(d.U),e.Y36(g.Kz))},a.\u0275cmp=e.Xpm({type:a,selectors:[["ng-component"]],decls:2,vars:1,consts:[["name","deleteForm",3,"ngSubmit",4,"ngIf"],["name","deleteForm",3,"ngSubmit"],[1,"modal-header"],["data-cy","featureDeleteDialogHeading","jhiTranslate","entity.delete.title",1,"modal-title"],["type","button","data-dismiss","modal","aria-hidden","true",1,"close",3,"click"],[1,"modal-body"],["id","jhi-delete-feature-heading","jhiTranslate","totalbattlecalcApp.feature.delete.question",3,"translateValues"],[1,"modal-footer"],["type","button","data-dismiss","modal",1,"btn","btn-secondary",3,"click"],["icon","ban"],["jhiTranslate","entity.action.cancel"],["id","jhi-confirm-delete-feature","data-cy","entityConfirmDeleteButton","type","submit",1,"btn","btn-danger"],["icon","times"],["jhiTranslate","entity.action.delete"]],template:function(t,o){1&t&&(e.YNc(0,F,38,3,"form",0),e._uU(1,"\n")),2&t&&e.Q6J("ngIf",o.feature)},directives:[T.O5,r._Y,r.JL,r.F,m.P,c.A,f.BN],encapsulation:2}),a})();var D=i(1066),h=i(6059),O=i(9044),x=i(4150);function M(a,_){1&a&&(e.TgZ(0,"div",12),e._uU(1,"\n    "),e.TgZ(2,"span",13),e._uU(3,"No features found"),e.qZA(),e._uU(4,"\n  "),e.qZA())}const A=function(a){return["/feature",a,"view"]},E=function(a){return["/feature",a,"edit"]};function q(a,_){if(1&a){const t=e.EpF();e.TgZ(0,"tr",28),e._uU(1,"\n          "),e.TgZ(2,"td"),e._uU(3,"\n            "),e.TgZ(4,"a",29),e._uU(5),e.qZA(),e._uU(6,"\n          "),e.qZA(),e._uU(7,"\n          "),e.TgZ(8,"td",30),e._uU(9),e.qZA(),e._uU(10,"\n          "),e.TgZ(11,"td"),e._uU(12),e.qZA(),e._uU(13,"\n          "),e.TgZ(14,"td",30),e._uU(15),e.qZA(),e._uU(16,"\n          "),e.TgZ(17,"td",31),e._uU(18,"\n            "),e.TgZ(19,"div",32),e._uU(20,"\n              "),e.TgZ(21,"button",33),e._uU(22,"\n                "),e._UZ(23,"fa-icon",34),e._uU(24,"\n                "),e.TgZ(25,"span",35),e._uU(26,"View"),e.qZA(),e._uU(27,"\n              "),e.qZA(),e._uU(28,"\n\n              "),e.TgZ(29,"button",36),e._uU(30,"\n                "),e._UZ(31,"fa-icon",37),e._uU(32,"\n                "),e.TgZ(33,"span",38),e._uU(34,"Edit"),e.qZA(),e._uU(35,"\n              "),e.qZA(),e._uU(36,"\n\n              "),e.TgZ(37,"button",39),e.NdJ("click",function(){const v=e.CHM(t).$implicit;return e.oxw(2).delete(v)}),e._uU(38,"\n                "),e._UZ(39,"fa-icon",40),e._uU(40,"\n                "),e.TgZ(41,"span",41),e._uU(42,"Delete"),e.qZA(),e._uU(43,"\n              "),e.qZA(),e._uU(44,"\n            "),e.qZA(),e._uU(45,"\n          "),e.qZA(),e._uU(46,"\n        "),e.qZA()}if(2&a){const t=_.$implicit;e.xp6(4),e.Q6J("routerLink",e.VKq(9,A,t.id)),e.xp6(1),e.Oqu(t.id),e.xp6(3),e.s9C("jhiTranslate","totalbattlecalcApp.FeatureName."+t.name),e.xp6(1),e.Oqu(t.name),e.xp6(3),e.Oqu(t.value),e.xp6(2),e.s9C("jhiTranslate","totalbattlecalcApp.MeasurementUnit."+t.unit),e.xp6(1),e.Oqu(t.unit),e.xp6(6),e.Q6J("routerLink",e.VKq(11,A,t.id)),e.xp6(8),e.Q6J("routerLink",e.VKq(13,E,t.id))}}function S(a,_){if(1&a){const t=e.EpF();e.TgZ(0,"div",14),e._uU(1,"\n    "),e.TgZ(2,"table",15),e._uU(3,"\n      "),e.TgZ(4,"thead"),e._uU(5,"\n        "),e.TgZ(6,"tr",16),e.NdJ("predicateChange",function(u){return e.CHM(t),e.oxw().predicate=u})("ascendingChange",function(u){return e.CHM(t),e.oxw().ascending=u})("sortChange",function(){return e.CHM(t),e.oxw().loadPage()}),e._uU(7,"\n          "),e.TgZ(8,"th",17),e.TgZ(9,"span",18),e._uU(10,"ID"),e.qZA(),e._uU(11," "),e._UZ(12,"fa-icon",19),e.qZA(),e._uU(13,"\n          "),e.TgZ(14,"th",20),e._uU(15,"\n            "),e.TgZ(16,"span",21),e._uU(17,"Name"),e.qZA(),e._uU(18," "),e._UZ(19,"fa-icon",19),e._uU(20,"\n          "),e.qZA(),e._uU(21,"\n          "),e.TgZ(22,"th",22),e._uU(23,"\n            "),e.TgZ(24,"span",23),e._uU(25,"Value"),e.qZA(),e._uU(26," "),e._UZ(27,"fa-icon",19),e._uU(28,"\n          "),e.qZA(),e._uU(29,"\n          "),e.TgZ(30,"th",24),e._uU(31,"\n            "),e.TgZ(32,"span",25),e._uU(33,"Unit"),e.qZA(),e._uU(34," "),e._UZ(35,"fa-icon",19),e._uU(36,"\n          "),e.qZA(),e._uU(37,"\n          "),e._UZ(38,"th",26),e._uU(39,"\n        "),e.qZA(),e._uU(40,"\n      "),e.qZA(),e._uU(41,"\n      "),e.TgZ(42,"tbody"),e._uU(43,"\n        "),e.YNc(44,q,47,15,"tr",27),e._uU(45,"\n      "),e.qZA(),e._uU(46,"\n    "),e.qZA(),e._uU(47,"\n  "),e.qZA()}if(2&a){const t=e.oxw();e.xp6(6),e.Q6J("predicate",t.predicate)("ascending",t.ascending),e.xp6(38),e.Q6J("ngForOf",t.features)("ngForTrackBy",t.trackId)}}const w=function(a,_,t){return{page:a,totalItems:_,itemsPerPage:t}};function K(a,_){if(1&a){const t=e.EpF();e.TgZ(0,"div"),e._uU(1,"\n    "),e.TgZ(2,"div",42),e._uU(3,"\n      "),e._UZ(4,"jhi-item-count",43),e._uU(5,"\n    "),e.qZA(),e._uU(6,"\n\n    "),e.TgZ(7,"div",42),e._uU(8,"\n      "),e.TgZ(9,"ngb-pagination",44),e.NdJ("pageChange",function(u){return e.CHM(t),e.oxw().ngbPaginationPage=u})("pageChange",function(u){return e.CHM(t),e.oxw().loadPage(u)}),e.qZA(),e._uU(10,"\n    "),e.qZA(),e._uU(11,"\n  "),e.qZA()}if(2&a){const t=e.oxw();e.xp6(4),e.Q6J("params",e.kEZ(7,w,t.page,t.totalItems,t.itemsPerPage)),e.xp6(5),e.Q6J("collectionSize",t.totalItems)("page",t.ngbPaginationPage)("pageSize",t.itemsPerPage)("maxSize",5)("rotate",!0)("boundaryLinks",!0)}}const Y=function(){return["/feature/new"]};let H=(()=>{class a{constructor(t,o,u,v){this.featureService=t,this.activatedRoute=o,this.router=u,this.modalService=v,this.isLoading=!1,this.totalItems=0,this.itemsPerPage=s.gK,this.ngbPaginationPage=1}loadPage(t,o){var u;this.isLoading=!0;const v=null!==(u=null!=t?t:this.page)&&void 0!==u?u:1;this.featureService.query({page:v-1,size:this.itemsPerPage,sort:this.sort()}).subscribe(j=>{this.isLoading=!1,this.onSuccess(j.body,j.headers,v,!o)},()=>{this.isLoading=!1,this.onError()})}ngOnInit(){this.handleNavigation()}trackId(t,o){return o.id}delete(t){const o=this.modalService.open(b,{size:"lg",backdrop:"static"});o.componentInstance.feature=t,o.closed.subscribe(u=>{"deleted"===u&&this.loadPage()})}sort(){const t=[this.predicate+","+(this.ascending?s.aW:s.jo)];return"id"!==this.predicate&&t.push("id"),t}handleNavigation(){(0,U.aj)([this.activatedRoute.data,this.activatedRoute.queryParamMap]).subscribe(([t,o])=>{var u;const v=o.get("page"),j=+(null!=v?v:1),J=(null!==(u=o.get(s._l))&&void 0!==u?u:t.defaultSort).split(","),R=J[0],Q=J[1]===s.aW;(j!==this.page||R!==this.predicate||Q!==this.ascending)&&(this.predicate=R,this.ascending=Q,this.loadPage(j,!0))})}onSuccess(t,o,u,v){this.totalItems=Number(o.get("X-Total-Count")),this.page=u,v&&this.router.navigate(["/feature"],{queryParams:{page:this.page,size:this.itemsPerPage,sort:this.predicate+","+(this.ascending?s.aW:s.jo)}}),this.features=null!=t?t:[],this.ngbPaginationPage=this.page}onError(){var t;this.ngbPaginationPage=null!==(t=this.page)&&void 0!==t?t:1}}return a.\u0275fac=function(t){return new(t||a)(e.Y36(d.U),e.Y36(p.gz),e.Y36(p.F0),e.Y36(g.FF))},a.\u0275cmp=e.Xpm({type:a,selectors:[["jhi-feature"]],decls:38,vars:7,consts:[["id","page-heading","data-cy","FeatureHeading"],["jhiTranslate","totalbattlecalcApp.feature.home.title"],[1,"d-flex","justify-content-end"],[1,"btn","btn-info","mr-2",3,"disabled","click"],["icon","sync",3,"spin"],["jhiTranslate","totalbattlecalcApp.feature.home.refreshListLabel"],["id","jh-create-entity","data-cy","entityCreateButton",1,"btn","btn-primary","jh-create-entity","create-feature",3,"routerLink"],["icon","plus"],["jhiTranslate","totalbattlecalcApp.feature.home.createLabel"],["class","alert alert-warning","id","no-result",4,"ngIf"],["class","table-responsive","id","entities",4,"ngIf"],[4,"ngIf"],["id","no-result",1,"alert","alert-warning"],["jhiTranslate","totalbattlecalcApp.feature.home.notFound"],["id","entities",1,"table-responsive"],["aria-describedby","page-heading",1,"table","table-striped"],["jhiSort","",3,"predicate","ascending","predicateChange","ascendingChange","sortChange"],["scope","col","jhiSortBy","id"],["jhiTranslate","global.field.id"],["icon","sort"],["scope","col","jhiSortBy","name"],["jhiTranslate","totalbattlecalcApp.feature.name"],["scope","col","jhiSortBy","value"],["jhiTranslate","totalbattlecalcApp.feature.value"],["scope","col","jhiSortBy","unit"],["jhiTranslate","totalbattlecalcApp.feature.unit"],["scope","col"],["data-cy","entityTable",4,"ngFor","ngForOf","ngForTrackBy"],["data-cy","entityTable"],[3,"routerLink"],[3,"jhiTranslate"],[1,"text-right"],[1,"btn-group"],["type","submit","data-cy","entityDetailsButton",1,"btn","btn-info","btn-sm",3,"routerLink"],["icon","eye"],["jhiTranslate","entity.action.view",1,"d-none","d-md-inline"],["type","submit","data-cy","entityEditButton",1,"btn","btn-primary","btn-sm",3,"routerLink"],["icon","pencil-alt"],["jhiTranslate","entity.action.edit",1,"d-none","d-md-inline"],["type","submit","data-cy","entityDeleteButton",1,"btn","btn-danger","btn-sm",3,"click"],["icon","times"],["jhiTranslate","entity.action.delete",1,"d-none","d-md-inline"],[1,"row","justify-content-center"],[3,"params"],[3,"collectionSize","page","pageSize","maxSize","rotate","boundaryLinks","pageChange"]],template:function(t,o){1&t&&(e.TgZ(0,"div"),e._uU(1,"\n  "),e.TgZ(2,"h2",0),e._uU(3,"\n    "),e.TgZ(4,"span",1),e._uU(5,"Features"),e.qZA(),e._uU(6,"\n\n    "),e.TgZ(7,"div",2),e._uU(8,"\n      "),e.TgZ(9,"button",3),e.NdJ("click",function(){return o.loadPage()}),e._uU(10,"\n        "),e._UZ(11,"fa-icon",4),e._uU(12,"\n        "),e.TgZ(13,"span",5),e._uU(14,"Refresh List"),e.qZA(),e._uU(15,"\n      "),e.qZA(),e._uU(16,"\n\n      "),e.TgZ(17,"button",6),e._uU(18,"\n        "),e._UZ(19,"fa-icon",7),e._uU(20,"\n        "),e.TgZ(21,"span",8),e._uU(22," Create a new Feature "),e.qZA(),e._uU(23,"\n      "),e.qZA(),e._uU(24,"\n    "),e.qZA(),e._uU(25,"\n  "),e.qZA(),e._uU(26,"\n\n  "),e._UZ(27,"jhi-alert-error"),e._uU(28,"\n\n  "),e._UZ(29,"jhi-alert"),e._uU(30,"\n\n  "),e.YNc(31,M,5,0,"div",9),e._uU(32,"\n\n  "),e.YNc(33,S,48,4,"div",10),e._uU(34,"\n\n  "),e.YNc(35,K,12,11,"div",11),e._uU(36,"\n"),e.qZA(),e._uU(37,"\n")),2&t&&(e.xp6(9),e.Q6J("disabled",o.isLoading),e.xp6(2),e.Q6J("spin",o.isLoading),e.xp6(6),e.Q6J("routerLink",e.DdM(6,Y)),e.xp6(14),e.Q6J("ngIf",0===(null==o.features?null:o.features.length)),e.xp6(2),e.Q6J("ngIf",o.features&&o.features.length>0),e.xp6(2),e.Q6J("ngIf",o.features&&o.features.length>0))},directives:[m.P,f.BN,p.rH,c.A,D.w,T.O5,h.b,O.T,T.sg,p.yS,x.N,g.N9],encapsulation:2}),a})();const W=function(a){return["/feature",a,"edit"]};function k(a,_){if(1&a){const t=e.EpF();e.TgZ(0,"div"),e._uU(1,"\n      "),e.TgZ(2,"h2",3),e.TgZ(3,"span",4),e._uU(4,"Feature"),e.qZA(),e.qZA(),e._uU(5,"\n\n      "),e._UZ(6,"hr"),e._uU(7,"\n\n      "),e._UZ(8,"jhi-alert-error"),e._uU(9,"\n\n      "),e._UZ(10,"jhi-alert"),e._uU(11,"\n\n      "),e.TgZ(12,"dl",5),e._uU(13,"\n        "),e.TgZ(14,"dt"),e.TgZ(15,"span",6),e._uU(16,"ID"),e.qZA(),e.qZA(),e._uU(17,"\n        "),e.TgZ(18,"dd"),e._uU(19,"\n          "),e.TgZ(20,"span"),e._uU(21),e.qZA(),e._uU(22,"\n        "),e.qZA(),e._uU(23,"\n        "),e.TgZ(24,"dt"),e.TgZ(25,"span",7),e._uU(26,"Name"),e.qZA(),e.qZA(),e._uU(27,"\n        "),e.TgZ(28,"dd"),e._uU(29,"\n          "),e.TgZ(30,"span",8),e._uU(31),e.qZA(),e._uU(32,"\n        "),e.qZA(),e._uU(33,"\n        "),e.TgZ(34,"dt"),e.TgZ(35,"span",9),e._uU(36,"Value"),e.qZA(),e.qZA(),e._uU(37,"\n        "),e.TgZ(38,"dd"),e._uU(39,"\n          "),e.TgZ(40,"span"),e._uU(41),e.qZA(),e._uU(42,"\n        "),e.qZA(),e._uU(43,"\n        "),e.TgZ(44,"dt"),e.TgZ(45,"span",10),e._uU(46,"Unit"),e.qZA(),e.qZA(),e._uU(47,"\n        "),e.TgZ(48,"dd"),e._uU(49,"\n          "),e.TgZ(50,"span",8),e._uU(51),e.qZA(),e._uU(52,"\n        "),e.qZA(),e._uU(53,"\n      "),e.qZA(),e._uU(54,"\n\n      "),e.TgZ(55,"button",11),e.NdJ("click",function(){return e.CHM(t),e.oxw().previousState()}),e._uU(56,"\n        "),e._UZ(57,"fa-icon",12),e._uU(58,"\xa0"),e.TgZ(59,"span",13),e._uU(60,"Back"),e.qZA(),e._uU(61,"\n      "),e.qZA(),e._uU(62,"\n\n      "),e.TgZ(63,"button",14),e._uU(64,"\n        "),e._UZ(65,"fa-icon",15),e._uU(66,"\xa0"),e.TgZ(67,"span",16),e._uU(68,"Edit"),e.qZA(),e._uU(69,"\n      "),e.qZA(),e._uU(70,"\n    "),e.qZA()}if(2&a){const t=e.oxw();e.xp6(21),e.Oqu(t.feature.id),e.xp6(9),e.s9C("jhiTranslate","totalbattlecalcApp.FeatureName."+t.feature.name),e.xp6(1),e.Oqu(t.feature.name),e.xp6(10),e.Oqu(t.feature.value),e.xp6(9),e.s9C("jhiTranslate","totalbattlecalcApp.MeasurementUnit."+t.feature.unit),e.xp6(1),e.Oqu(t.feature.unit),e.xp6(12),e.Q6J("routerLink",e.VKq(7,W,t.feature.id))}}let z=(()=>{class a{constructor(t){this.activatedRoute=t,this.feature=null}ngOnInit(){this.activatedRoute.data.subscribe(({feature:t})=>{this.feature=t})}previousState(){window.history.back()}}return a.\u0275fac=function(t){return new(t||a)(e.Y36(p.gz))},a.\u0275cmp=e.Xpm({type:a,selectors:[["jhi-feature-detail"]],decls:8,vars:1,consts:[[1,"row","justify-content-center"],[1,"col-8"],[4,"ngIf"],["data-cy","featureDetailsHeading"],["jhiTranslate","totalbattlecalcApp.feature.detail.title"],[1,"row-md","jh-entity-details"],["jhiTranslate","global.field.id"],["jhiTranslate","totalbattlecalcApp.feature.name"],[3,"jhiTranslate"],["jhiTranslate","totalbattlecalcApp.feature.value"],["jhiTranslate","totalbattlecalcApp.feature.unit"],["type","submit","data-cy","entityDetailsBackButton",1,"btn","btn-info",3,"click"],["icon","arrow-left"],["jhiTranslate","entity.action.back"],["type","button",1,"btn","btn-primary",3,"routerLink"],["icon","pencil-alt"],["jhiTranslate","entity.action.edit"]],template:function(t,o){1&t&&(e.TgZ(0,"div",0),e._uU(1,"\n  "),e.TgZ(2,"div",1),e._uU(3,"\n    "),e.YNc(4,k,71,9,"div",2),e._uU(5,"\n  "),e.qZA(),e._uU(6,"\n"),e.qZA(),e._uU(7,"\n")),2&t&&(e.xp6(4),e.Q6J("ngIf",o.feature))},directives:[T.O5,m.P,c.A,D.w,f.BN,p.rH],encapsulation:2}),a})();var V=i(7813),L=i(5698),P=(()=>{return(a=P||(P={})).HUMAN="HUMAN",a.RANGED="RANGED",a.MELEE="MELEE",a.MOUNTED="MOUNTED",a.GUARDSMAN="GUARDSMAN",P;var a})(),$=i(1426),G=i(1158);function X(a,_){if(1&a&&(e.TgZ(0,"option",22),e._uU(1),e.ALo(2,"translate"),e.qZA()),2&a){const t=_.$implicit;e.Q6J("value",t),e.xp6(1),e.hij("\n              ",e.lcZ(2,2,"totalbattlecalcApp.FeatureName."+t),"\n            ")}}function ee(a,_){if(1&a&&(e.TgZ(0,"option",22),e._uU(1),e.ALo(2,"translate"),e.qZA()),2&a){const t=_.$implicit;e.Q6J("value",t),e.xp6(1),e.hij("\n              ",e.lcZ(2,2,"totalbattlecalcApp.MeasurementUnit."+t),"\n            ")}}let N=(()=>{class a{constructor(t,o,u){this.featureService=t,this.activatedRoute=o,this.fb=u,this.isSaving=!1,this.featureNameValues=Object.keys(P),this.measurementUnitValues=Object.keys($.q),this.editForm=this.fb.group({id:[],name:[],value:[],unit:[]})}ngOnInit(){this.activatedRoute.data.subscribe(({feature:t})=>{this.updateForm(t)})}previousState(){window.history.back()}save(){this.isSaving=!0;const t=this.createFromForm();this.subscribeToSaveResponse(void 0!==t.id?this.featureService.update(t):this.featureService.create(t))}subscribeToSaveResponse(t){t.pipe((0,V.x)(()=>this.onSaveFinalize())).subscribe(()=>this.onSaveSuccess(),()=>this.onSaveError())}onSaveSuccess(){this.previousState()}onSaveError(){}onSaveFinalize(){this.isSaving=!1}updateForm(t){this.editForm.patchValue({id:t.id,name:t.name,value:t.value,unit:t.unit})}createFromForm(){return Object.assign(Object.assign({},new L.L),{id:this.editForm.get(["id"]).value,name:this.editForm.get(["name"]).value,value:this.editForm.get(["value"]).value,unit:this.editForm.get(["unit"]).value})}}return a.\u0275fac=function(t){return new(t||a)(e.Y36(d.U),e.Y36(p.gz),e.Y36(r.qu))},a.\u0275cmp=e.Xpm({type:a,selectors:[["jhi-feature-update"]],decls:82,vars:14,consts:[[1,"row","justify-content-center"],[1,"col-8"],["name","editForm","role","form","novalidate","",3,"formGroup","ngSubmit"],["id","jhi-feature-heading","data-cy","FeatureCreateUpdateHeading","jhiTranslate","totalbattlecalcApp.feature.home.createOrEditLabel"],[1,"form-group",3,"hidden"],["jhiTranslate","global.field.id","for","field_id",1,"form-control-label"],["type","number","name","id","id","field_id","data-cy","id","formControlName","id",1,"form-control",3,"readonly"],[1,"form-group"],["jhiTranslate","totalbattlecalcApp.feature.name","for","field_name",1,"form-control-label"],["name","name","formControlName","name","id","field_name","data-cy","name",1,"form-control"],[3,"ngValue"],[3,"value",4,"ngFor","ngForOf"],["jhiTranslate","totalbattlecalcApp.feature.value","for","field_value",1,"form-control-label"],["type","number","name","value","id","field_value","data-cy","value","formControlName","value",1,"form-control"],["jhiTranslate","totalbattlecalcApp.feature.unit","for","field_unit",1,"form-control-label"],["name","unit","formControlName","unit","id","field_unit","data-cy","unit",1,"form-control"],["type","button","id","cancel-save","data-cy","entityCreateCancelButton",1,"btn","btn-secondary",3,"click"],["icon","ban"],["jhiTranslate","entity.action.cancel"],["type","submit","id","save-entity","data-cy","entityCreateSaveButton",1,"btn","btn-primary",3,"disabled"],["icon","save"],["jhiTranslate","entity.action.save"],[3,"value"]],template:function(t,o){1&t&&(e.TgZ(0,"div",0),e._uU(1,"\n  "),e.TgZ(2,"div",1),e._uU(3,"\n    "),e.TgZ(4,"form",2),e.NdJ("ngSubmit",function(){return o.save()}),e._uU(5,"\n      "),e.TgZ(6,"h2",3),e._uU(7,"\n        Create or edit a Feature\n      "),e.qZA(),e._uU(8,"\n\n      "),e.TgZ(9,"div"),e._uU(10,"\n        "),e._UZ(11,"jhi-alert-error"),e._uU(12,"\n\n        "),e.TgZ(13,"div",4),e._uU(14,"\n          "),e.TgZ(15,"label",5),e._uU(16,"ID"),e.qZA(),e._uU(17,"\n          "),e._UZ(18,"input",6),e._uU(19,"\n        "),e.qZA(),e._uU(20,"\n\n        "),e.TgZ(21,"div",7),e._uU(22,"\n          "),e.TgZ(23,"label",8),e._uU(24,"Name"),e.qZA(),e._uU(25,"\n          "),e.TgZ(26,"select",9),e._uU(27,"\n            "),e.TgZ(28,"option",10),e._uU(29),e.ALo(30,"translate"),e.qZA(),e._uU(31,"\n            "),e.YNc(32,X,3,4,"option",11),e._uU(33,"\n          "),e.qZA(),e._uU(34,"\n        "),e.qZA(),e._uU(35,"\n\n        "),e.TgZ(36,"div",7),e._uU(37,"\n          "),e.TgZ(38,"label",12),e._uU(39,"Value"),e.qZA(),e._uU(40,"\n          "),e._UZ(41,"input",13),e._uU(42,"\n        "),e.qZA(),e._uU(43,"\n\n        "),e.TgZ(44,"div",7),e._uU(45,"\n          "),e.TgZ(46,"label",14),e._uU(47,"Unit"),e.qZA(),e._uU(48,"\n          "),e.TgZ(49,"select",15),e._uU(50,"\n            "),e.TgZ(51,"option",10),e._uU(52),e.ALo(53,"translate"),e.qZA(),e._uU(54,"\n            "),e.YNc(55,ee,3,4,"option",11),e._uU(56,"\n          "),e.qZA(),e._uU(57,"\n        "),e.qZA(),e._uU(58,"\n      "),e.qZA(),e._uU(59,"\n\n      "),e.TgZ(60,"div"),e._uU(61,"\n        "),e.TgZ(62,"button",16),e.NdJ("click",function(){return o.previousState()}),e._uU(63,"\n          "),e._UZ(64,"fa-icon",17),e._uU(65,"\xa0"),e.TgZ(66,"span",18),e._uU(67,"Cancel"),e.qZA(),e._uU(68,"\n        "),e.qZA(),e._uU(69,"\n\n        "),e.TgZ(70,"button",19),e._uU(71,"\n          "),e._UZ(72,"fa-icon",20),e._uU(73,"\xa0"),e.TgZ(74,"span",21),e._uU(75,"Save"),e.qZA(),e._uU(76,"\n        "),e.qZA(),e._uU(77,"\n      "),e.qZA(),e._uU(78,"\n    "),e.qZA(),e._uU(79,"\n  "),e.qZA(),e._uU(80,"\n"),e.qZA(),e._uU(81,"\n")),2&t&&(e.xp6(4),e.Q6J("formGroup",o.editForm),e.xp6(9),e.Q6J("hidden",null==o.editForm.get("id").value),e.xp6(5),e.Q6J("readonly",!0),e.xp6(10),e.Q6J("ngValue",null),e.xp6(1),e.Oqu(e.lcZ(30,10,"totalbattlecalcApp.FeatureName.null")),e.xp6(3),e.Q6J("ngForOf",o.featureNameValues),e.xp6(19),e.Q6J("ngValue",null),e.xp6(1),e.Oqu(e.lcZ(53,12,"totalbattlecalcApp.MeasurementUnit.null")),e.xp6(3),e.Q6J("ngForOf",o.measurementUnitValues),e.xp6(15),e.Q6J("disabled",o.editForm.invalid||o.isSaving))},directives:[r._Y,r.JL,r.sg,m.P,c.A,r.wV,r.Fj,r.JJ,r.u,r.EJ,r.YN,r.Kr,T.sg,f.BN],pipes:[G.X$],encapsulation:2}),a})();var B=i(267),te=i(3974),ne=i(6513);let I=(()=>{class a{constructor(t,o){this.service=t,this.router=o}resolve(t){const o=t.params.id;return o?this.service.find(o).pipe((0,ne.zg)(u=>u.body?(0,B.of)(u.body):(this.router.navigate(["404"]),te.E))):(0,B.of)(new L.L)}}return a.\u0275fac=function(t){return new(t||a)(e.LFG(d.U),e.LFG(p.F0))},a.\u0275prov=e.Yz7({token:a,factory:a.\u0275fac,providedIn:"root"}),a})();const ae=[{path:"",component:H,data:{defaultSort:"id,asc"},canActivate:[l.Z]},{path:":id/view",component:z,resolve:{feature:I},canActivate:[l.Z]},{path:"new",component:N,resolve:{feature:I},canActivate:[l.Z]},{path:":id/edit",component:N,resolve:{feature:I},canActivate:[l.Z]}];let ie=(()=>{class a{}return a.\u0275fac=function(t){return new(t||a)},a.\u0275mod=e.oAB({type:a}),a.\u0275inj=e.cJS({imports:[[p.Bz.forChild(ae)],p.Bz]}),a})(),re=(()=>{class a{}return a.\u0275fac=function(t){return new(t||a)},a.\u0275mod=e.oAB({type:a}),a.\u0275inj=e.cJS({imports:[[n.m,ie]]}),a})()},6662:(C,Z,i)=>{i.d(Z,{A:()=>T});var n=i(2741),p=i(6912),l=i(9161),U=i(1158),s=i(6274),e=i(2444);function d(r,m){if(1&r){const c=n.EpF();n.TgZ(0,"ngb-alert",4),n.NdJ("closed",function(){n.CHM(c);const y=n.oxw().$implicit;return n.oxw().close(y)}),n._uU(1,"\n      "),n._UZ(2,"pre",5),n._uU(3,"\n    "),n.qZA()}if(2&r){const c=n.oxw().$implicit;n.Q6J("type",c.type),n.xp6(2),n.Q6J("innerHTML",c.message,n.oJD)}}function g(r,m){if(1&r&&(n.TgZ(0,"div",2),n._uU(1,"\n    "),n.YNc(2,d,4,2,"ngb-alert",3),n._uU(3,"\n  "),n.qZA()),2&r){const c=m.$implicit,f=n.oxw();n.Q6J("ngClass",f.setClasses(c)),n.xp6(2),n.Q6J("ngIf",c.message)}}let T=(()=>{class r{constructor(c,f,y){this.alertService=c,this.eventManager=f,this.alerts=[],this.errorListener=f.subscribe("totalbattlecalcApp.error",F=>{const b=F.content;this.addErrorAlert(b.message,b.key,b.params)}),this.httpErrorListener=f.subscribe("totalbattlecalcApp.httpError",F=>{var b,D;const h=F.content;switch(h.status){case 0:this.addErrorAlert("Server not reachable","error.server.not.reachable");break;case 400:{const O=h.headers.keys();let x=null,M=null;for(const A of O)A.toLowerCase().endsWith("app-error")?x=h.headers.get(A):A.toLowerCase().endsWith("app-params")&&(M=h.headers.get(A));if(x){const A=M?{entityName:y.instant(`global.menu.entities.${M}`)}:void 0;this.addErrorAlert(x,x,A)}else if(""!==h.error&&h.error.fieldErrors){const A=h.error.fieldErrors;for(const E of A){["Min","Max","DecimalMin","DecimalMax"].includes(E.message)&&(E.message="Size");const q=E.field.replace(/\[\d*\]/g,"[]"),S=y.instant(`totalbattlecalcApp.${E.objectName}.${q}`);this.addErrorAlert(`Error on field "${S}"`,`error.${E.message}`,{fieldName:S})}}else""!==h.error&&h.error.message?this.addErrorAlert(null!==(b=h.error.detail)&&void 0!==b?b:h.error.message,h.error.message,h.error.params):this.addErrorAlert(h.error,h.error);break}case 404:this.addErrorAlert("Not found","error.url.not.found");break;default:""!==h.error&&h.error.message?this.addErrorAlert(null!==(D=h.error.detail)&&void 0!==D?D:h.error.message,h.error.message,h.error.params):this.addErrorAlert(h.error,h.error)}})}setClasses(c){const f={"jhi-toast":Boolean(c.toast)};return c.position?Object.assign(Object.assign({},f),{[c.position]:!0}):f}ngOnDestroy(){this.eventManager.destroy(this.errorListener),this.eventManager.destroy(this.httpErrorListener)}close(c){var f;null===(f=c.close)||void 0===f||f.call(c,this.alerts)}addErrorAlert(c,f,y){this.alertService.addAlert({type:"danger",message:c,translationKey:f,translationParams:y},this.alerts)}}return r.\u0275fac=function(c){return new(c||r)(n.Y36(p.c),n.Y36(l.Q),n.Y36(U.sK))},r.\u0275cmp=n.Xpm({type:r,selectors:[["jhi-alert-error"]],decls:5,vars:1,consts:[["role","alert",1,"alerts"],[3,"ngClass",4,"ngFor","ngForOf"],[3,"ngClass"],[3,"type","closed",4,"ngIf"],[3,"type","closed"],[3,"innerHTML"]],template:function(c,f){1&c&&(n.TgZ(0,"div",0),n._uU(1,"\n  "),n.YNc(2,g,4,2,"div",1),n._uU(3,"\n"),n.qZA(),n._uU(4,"\n")),2&c&&(n.xp6(2),n.Q6J("ngForOf",f.alerts))},directives:[s.sg,s.mk,s.O5,e.xm],encapsulation:2}),r})()},1066:(C,Z,i)=>{i.d(Z,{w:()=>d});var n=i(2741),p=i(6912),l=i(6274),U=i(2444);function s(g,T){if(1&g){const r=n.EpF();n.TgZ(0,"ngb-alert",4),n.NdJ("closed",function(){n.CHM(r);const c=n.oxw().$implicit;return n.oxw().close(c)}),n._uU(1,"\n      "),n._UZ(2,"pre",5),n._uU(3,"\n    "),n.qZA()}if(2&g){const r=n.oxw().$implicit;n.Q6J("type",r.type),n.xp6(2),n.Q6J("innerHTML",r.message,n.oJD)}}function e(g,T){if(1&g&&(n.TgZ(0,"div",2),n._uU(1,"\n    "),n.YNc(2,s,4,2,"ngb-alert",3),n._uU(3,"\n  "),n.qZA()),2&g){const r=T.$implicit,m=n.oxw();n.Q6J("ngClass",m.setClasses(r)),n.xp6(2),n.Q6J("ngIf",r.message)}}let d=(()=>{class g{constructor(r){this.alertService=r,this.alerts=[]}ngOnInit(){this.alerts=this.alertService.get()}setClasses(r){const m={"jhi-toast":Boolean(r.toast)};return r.position?Object.assign(Object.assign({},m),{[r.position]:!0}):m}ngOnDestroy(){this.alertService.clear()}close(r){var m;null===(m=r.close)||void 0===m||m.call(r,this.alerts)}}return g.\u0275fac=function(r){return new(r||g)(n.Y36(p.c))},g.\u0275cmp=n.Xpm({type:g,selectors:[["jhi-alert"]],decls:5,vars:1,consts:[["role","alert",1,"alerts"],[3,"ngClass",4,"ngFor","ngForOf"],[3,"ngClass"],[3,"type","closed",4,"ngIf"],[3,"type","closed"],[3,"innerHTML"]],template:function(r,m){1&r&&(n.TgZ(0,"div",0),n._uU(1,"\n  "),n.YNc(2,e,4,2,"div",1),n._uU(3,"\n"),n.qZA(),n._uU(4,"\n")),2&r&&(n.xp6(2),n.Q6J("ngForOf",m.alerts))},directives:[l.sg,l.mk,l.O5,U.xm],encapsulation:2}),g})()},4150:(C,Z,i)=>{i.d(Z,{N:()=>U});var n=i(2741),p=i(4243);const l=function(s,e,d){return{first:s,second:e,total:d}};let U=(()=>{class s{set params(d){d.page&&void 0!==d.totalItems&&d.itemsPerPage?(this.first=(d.page-1)*d.itemsPerPage+1,this.second=d.page*d.itemsPerPage<d.totalItems?d.page*d.itemsPerPage:d.totalItems):(this.first=void 0,this.second=void 0),this.total=d.totalItems}}return s.\u0275fac=function(d){return new(d||s)},s.\u0275cmp=n.Xpm({type:s,selectors:[["jhi-item-count"]],inputs:{params:"params"},decls:3,vars:5,consts:[["jhiTranslate","global.item-count",3,"translateValues"]],template:function(d,g){1&d&&(n._uU(0," "),n._UZ(1,"div",0),n._uU(2," ")),2&d&&(n.xp6(1),n.Q6J("translateValues",n.kEZ(1,l,g.first,g.second,g.total)))},directives:[p.P],encapsulation:2}),s})()},9044:(C,Z,i)=>{i.d(Z,{T:()=>d});var n=i(6006),p=i(7194),l=i(4477),U=i(8036),s=i(2741),e=i(6059);let d=(()=>{class g{constructor(r){this.sort=r,this.sortIcon=U.CmM,this.sortAscIcon=U.foy,this.sortDescIcon=U.u9C,this.destroy$=new n.xQ,r.predicateChange.pipe((0,p.R)(this.destroy$)).subscribe(()=>this.updateIconDefinition()),r.ascendingChange.pipe((0,p.R)(this.destroy$)).subscribe(()=>this.updateIconDefinition())}onClick(){this.iconComponent&&this.sort.sort(this.jhiSortBy)}ngAfterContentInit(){this.updateIconDefinition()}ngOnDestroy(){this.destroy$.next(),this.destroy$.complete()}updateIconDefinition(){if(this.iconComponent){let r=this.sortIcon;this.sort.predicate===this.jhiSortBy&&(r=this.sort.ascending?this.sortAscIcon:this.sortDescIcon),this.iconComponent.icon=r.iconName,this.iconComponent.render()}}}return g.\u0275fac=function(r){return new(r||g)(s.Y36(e.b,1))},g.\u0275dir=s.lG2({type:g,selectors:[["","jhiSortBy",""]],contentQueries:function(r,m,c){if(1&r&&s.Suo(c,l.BN,5),2&r){let f;s.iGM(f=s.CRH())&&(m.iconComponent=f.first)}},hostBindings:function(r,m){1&r&&s.NdJ("click",function(){return m.onClick()})},inputs:{jhiSortBy:"jhiSortBy"}}),g})()},6059:(C,Z,i)=>{i.d(Z,{b:()=>p});var n=i(2741);let p=(()=>{class l{constructor(){this.predicateChange=new n.vpe,this.ascendingChange=new n.vpe,this.sortChange=new n.vpe}get predicate(){return this._predicate}set predicate(s){this._predicate=s,this.predicateChange.emit(s)}get ascending(){return this._ascending}set ascending(s){this._ascending=s,this.ascendingChange.emit(s)}sort(s){this.ascending=s!==this.predicate||!this.ascending,this.predicate=s,this.predicateChange.emit(s),this.ascendingChange.emit(this.ascending),this.sortChange.emit({predicate:this.predicate,ascending:this.ascending})}}return l.\u0275fac=function(s){return new(s||l)},l.\u0275dir=n.lG2({type:l,selectors:[["","jhiSort",""]],inputs:{predicate:"predicate",ascending:"ascending"},outputs:{predicateChange:"predicateChange",ascendingChange:"ascendingChange",sortChange:"sortChange"}}),l})()}}]);