export type LoginRequest = {
  email: string,
  password: string
}

export type RegisterRequest = {
  username: string,
  email: string,
  password: string,
  profilePictureUrl?: string,
}
