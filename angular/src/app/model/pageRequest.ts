export class PageRequest {
  pageNumber: number;
  pageLimit: number;
  orderBy: string;
  orderType: string;
  constructor() {
    this.pageNumber = 1;
    this.pageLimit = 10;
  }
}
