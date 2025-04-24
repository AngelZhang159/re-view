export interface SearchMultiResponse {
  page: number
  results: SearchMultiBody[]
  totalPages: number
  totalResults: number
}

export interface SearchMultiBody {
  backdropPath?: string
  id: number
  title?: string
  originalTitle?: string
  overview: string
  posterPath: string
  mediaType: string
  adult: boolean
  originalLanguage: string
  genreIds: number[]
  popularity: number
  releaseDate?: string
  video?: boolean
  voteAverage: number
  voteCount: number
  name?: string
  originalName?: string
  firstAirDate?: string
  originCountry?: string[]
}
