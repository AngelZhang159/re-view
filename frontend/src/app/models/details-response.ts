export type DetailsResponse = {
  adult: boolean,
  backdropPath: string,
  belongsToCollection: BelongsToCollection,
  budget: number,
  genres: Genre[],
  homepage: string,
  id: number,
  imdbId: string,
  originCountry: CountryCode[],
  originalLanguage: string,
  originalTitle: string,
  overview: string,
  popularity: number,
  posterPath: string,
  productionCompanies: ProductionCompany[],
  productionCountries: ProductionCountry[],
  releaseDate: string,
  revenue: number,
  runtime: number,
  spokenLanguages: SpokenLanguage[],
  status: string,
  tagline: string,
  title: string,
  video: boolean,
  voteAverage: number,
  voteCount: number,
  createdBy: CreatedBy[],
  episodeRunTime: number[],
  firstAirDate: string,
  inProduction: boolean,
  languages: string[],
  lastAirDate: string,
  lastEpisodeToAir: Episode,
  name: string,
  nextEpisodeToAir: Episode,
  networks: Network[],
  numberOfEpisodes: number,
  numberOfSeasons: number,
  originalName: string,
  seasons: Season[],
  type: string,
  alsoKnownAs: string[],
  biography: string,
  birthday: string,
  deathday: string,
  gender: number,
  knownForDepartment: string,
  placeOfBirth: string,
  profilePath: string,
  mediaType: string,
}

export type BelongsToCollection = {
  id: number,
  name: string,
  posterPath: string,
  backdropPath: string
}

export type Genre = {
  id: number,
  name: string
}

export type ProductionCompany = {
  id: number,
  logoPath: string,
  name: string,
  originCountry: string
}

export type ProductionCountry = {
  iso_3166_1: string,
  name: string
}

export type SpokenLanguage = {
  englishName: string,
  iso_639_1: string,
  name: string
}

export type CreatedBy = {
  id: number,
  creditId: string,
  name: string,
  originalName: string,
  gender: number,
  profilePath: string
}

export type Episode = {
  id: number,
  name: string,
  overview: string,
  voteAverage: number,
  voteCount: number
  airDate: string,
  episodeNumber: number,
  episodeType: string,
  productionCode: string,
  runtime: number,
  seasonNumber: number,
  showId: number,
  stillPath: string,
}

export type Network = {
  id: number,
  logoPath: string,
  name: string,
  originCountry: string
}

export type Season = {
  id: number,
  airDate: string,
  episodeCount: number,
  name: string,
  overview: string,
  posterPath: string,
  seasonNumber: number,
  voteAverage: number
}

export enum CountryCode {
  AF, AX, AL, DZ, AS, AD, AO, AI, AQ, AG,
  AR, AM, AW, AU, AT, AZ, BS, BH, BD, BB,
  BY, BE, BZ, BJ, BM, BT, BO, BQ, BA, BW,
  BV, BR, IO, BN, BG, BF, BI, CV, KH, CM,
  CA, KY, CF, TD, CL, CN, CX, CC, CO, KM,
  CG, CD, CK, CR, CI, HR, CU, CW, CY, CZ,
  DK, DJ, DM, DO, EC, EG, SV, GQ, ER, EE,
  SZ, ET, FK, FO, FJ, FI, FR, GF, PF, TF,
  GA, GM, GE, DE, GH, GI, GR, GL, GD, GP,
  GU, GT, GG, GN, GW, GY, HT, HM, VA, HN,
  HK, HU, IS, IN, ID, IR, IQ, IE, IM, IL,
  IT, JM, JP, JE, JO, KZ, KE, KI, KP, KR,
  KW, KG, LA, LV, LB, LS, LR, LY, LI, LT,
  LU, MO, MG, MW, MY, MV, ML, MT, MH, MQ,
  MR, MU, YT, MX, FM, MD, MC, MN, ME, MS,
  MA, MZ, MM, NA, NR, NP, NL, NC, NZ, NI,
  NE, NG, NU, NF, MK, MP, NO, OM, PK, PW,
  PS, PA, PG, PY, PE, PH, PN, PL, PT, PR,
  QA, RE, RO, RU, RW, BL, SH, KN, LC, MF,
  PM, VC, WS, SM, ST, SA, SN, RS, SC, SL,
  SG, SX, SK, SI, SB, SO, ZA, GS, SS, ES,
  LK, SD, SR, SJ, SE, CH, SY, TW, TJ, TZ,
  TH, TL, TG, TK, TO, TT, TN, TR, TM, TC,
  TV, UG, UA, AE, GB, US, UM, UY, UZ, VU,
  VE, VN, VG, VI, WF, EH, YE, ZM, ZW
}
