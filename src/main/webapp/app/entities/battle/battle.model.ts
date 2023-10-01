import * as dayjs from 'dayjs';

export interface IBattle {
  id?: number;
  date?: dayjs.Dayjs | null;
}

export class Battle implements IBattle {
  constructor(public id?: number, public date?: dayjs.Dayjs | null) {}
}

export function getBattleIdentifier(battle: IBattle): number | undefined {
  return battle.id;
}
