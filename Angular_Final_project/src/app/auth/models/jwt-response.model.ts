export interface JwtResponse {
   token: string;       // Angular code expects this
  username?: string;
  roles?: string[];
}
