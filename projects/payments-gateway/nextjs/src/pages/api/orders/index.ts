import { withIronSessionApiRoute } from "iron-session/next";
import ironConfig from "../../../utils/iron-config";
import { NextApiRequest, NextApiResponse } from "next";
import axios from "axios";

export default withIronSessionApiRoute(ordersList, ironConfig);

async function ordersList(req: NextApiRequest, res: NextApiResponse) {
  const account = req.session.account;

  if (!account) {
    return res.status(401).json({ message: "Unauthenticated" });
  }

  try {
    const { data } = await axios.get(`${process.env.NEST_API_HOST}/orders`, {
      headers: {
        "x-api-token": account.token,
      },
    });

    res.status(200).json(data);
  } catch (e) {
    if (axios.isAxiosError(e)) {
      res.status(e.response!.status).json(e.response?.data);
    } else {
      res.status(500).json({ message: "Server error" });
    }
  }
}
