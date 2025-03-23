export type DetailsResponse = {
  adult: boolean,
  backdrop_path: string,
  belongs_to_collection: BelongsToCollection,
  budget: number,
  genres: Genre[],
  homepage: string,
  id: number,
  imdb_id: string,
  origin_country: CountryCode[],
  original_language: string,
  original_title: string,
  overview: string,
  popularity: number,
  poster_path: string,
  production_companies: ProductionCompany[],
  production_countries: ProductionCountry[],
  release_date: string,
  revenue: number,
  runtime: number,
  spoken_languages: SpokenLanguage[],
  status: string,
  tagline: string,
  title: string,
  video: boolean,
  vote_average: number,
  vote_count: number,
  created_by: CreatedBy[],
  episode_run_time: number[],
  first_air_date: string,
  in_production: boolean,
  languages: string[],
  last_air_date: string,
  last_episode_to_air: Episode,
  name: string,
  next_episode_to_air: Episode,
  networks: Network[],
  number_of_episodes: number,
  number_of_seasons: number,
  original_name: string,
  seasons: Season[],
  type: string,
  also_known_as: string[],
  biography: string,
  birthday: string,
  deathday: string,
  gender: number,
  known_for_department: string,
  place_of_birth: string,
  profile_path: string
}

export type BelongsToCollection = {
  id: number,
  name: string,
  poster_path: string,
  backdrop_path: string
}

export type Genre = {
  id: number,
  name: string
}

export type ProductionCompany = {
  id: number,
  logo_path: string,
  name: string,
  origin_country: string
}

export type ProductionCountry = {
  iso_3166_1: string,
  name: string
}

export type SpokenLanguage = {
  english_name: string,
  iso_639_1: string,
  name: string
}

export type CreatedBy = {
  id: number,
  credit_id: string,
  name: string,
  original_name: string,
  gender: number,
  profile_path: string
}

export type Episode = {
  id: number,
  name: string,
  overview: string,
  vote_average: number,
  vote_count: number
  air_date: string,
  episode_number: number,
  episode_type: string,
  production_code: string,
  runtime: number,
  season_number: number,
  show_id: number,
  still_path: string,
}

export type Network = {
  id: number,
  logo_path: string,
  name: string,
  origin_country: string
}

export type Season = {
  id: number,
  air_date: string,
  episode_count: number,
  name: string,
  overview: string,
  poster_path: string,
  season_number: number,
  vote_average: number
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
