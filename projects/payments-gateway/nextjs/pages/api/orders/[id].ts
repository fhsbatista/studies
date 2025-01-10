import { withIronSessionApiRoute } from "iron-session/next";
import ironConfig from "../../../utils/iron-config";
import { NextApiRequest, NextApiResponse } from "next";
import axios from "axios";

export default withIronSessionApiRoute(getOrder, ironConfig);

async function getOrder(req: NextApiRequest, res: NextApiResponse) {
  const account = req.session.account;

  if (!account) {
    return res.status(401).json({ message: "Unauthenticated" });
  }

  const { id } = req.query;

  try {
    const { data } = await axios.get(`http://localhost:3000/orders/${id}`, {
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
