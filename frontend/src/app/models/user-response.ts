export interface UserResponse {
  accessToken: string,
  refreshToken: string,
  user: User,
  expiresIn: number
}

export interface User {
  username: string,
  email: string,
  profilePicture: string
}
