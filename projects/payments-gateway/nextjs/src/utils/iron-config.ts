const ironConfig = {
  password: process.env.COOKIE_KEY,
  cookieName: "payments-gateway-session",
  cookieOptions: {
    secure: process.env.NODE_ENV === "production" ? true : false,
  }
};

declare module "iron-session" {
  interface IronSessionData {
    account?: {
      id: number;
      name: string;
      token: string;
    };
  }
}

export default ironConfig;