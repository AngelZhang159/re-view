export type UserLoginResponse = {
  accessToken: string,
  refreshToken: string,
  user: User,
  expiresIn: number
}

export type UserRegisterResponse = {
  id: number,
  username: string,
  email: string,
  message: string
}

export type User = {
  username: string,
  email: string,
  profilePictureUrl: string
}
