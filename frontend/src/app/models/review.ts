export type ReviewRequest = {
  type: string,
  mediaId: number,
  review: string,
  rating: number,
  seasonsWatched?: number,
}

export type ReviewResponsePage = {
  content: ReviewResponse[],
  pageable: Pageable,
  last: boolean,
  totalPages: number,
  totalElements: number,
  size: number,
  number: number,
  sort: Sort,
  numberOfElements: number,
  first: boolean,
  empty: boolean,
}

export type ReviewResponse = {
  id: number,
  userId: string,
  mediaType: string,
  mediaId: number,
  mediaTitle: string,
  mediaPosterPath: string,
  review: string,
  rating: number,
  seasons: number,
  seasonsWatched: number,
  createdAt: string,
  updatedAt: string,
}

export type Pageable = {
  pageNumber: number,
  pageSize: number,
  sort: Sort,
  offset: number,
  paged: boolean,
  unpaged: boolean,
}

export type Sort = {
  sorted: boolean,
  unsorted: boolean,
  empty: boolean,
}
