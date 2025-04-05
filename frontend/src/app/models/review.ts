export type ReviewRequest = {
  type: string,
  mediaId: number,
  review: string,
  rating: number,
  seasonsWatched?: number,
}
