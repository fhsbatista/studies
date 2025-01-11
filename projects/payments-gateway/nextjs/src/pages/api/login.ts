import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";
import { withIronSessionApiRoute } from "iron-session/next";
import ironConfig from "../../utils/iron-config";
import Cors from 'cors';

export default withIronSessionApiRoute(login, ironConfig);

const cors = Cors({
  methods: ['GET', 'POST', 'PUT', 'DELETE', 'OPTIONS'],
  origin: '*', // Permite todas as origens. Alterar para um domínio específico se necessário
  allowedHeaders: ['Content-Type', 'Authorization'], // Cabeçalhos permitidos
});

function runMiddleware(req: NextApiRequest, res: NextApiResponse, fn: Function) {
  console.log("excutando middleware");

  return new Promise<void>((resolve, reject) => {
    fn(req, res, (result: Error | void) => {
      if (result instanceof Error) {
        return reject(result);
      }

      console.log("cors liberado");
      return resolve();
    });
  });
}

async function login(req: NextApiRequest, res: NextApiResponse) {
  console.log("excutando api");
  await runMiddleware(req, res, cors);

  const { token } = req.body;
  try {
    const { data } = await axios.get(
      `${process.env.NEST_API_HOST}/accounts/${token}`
    );
    req.session.account = data;
    await req.session.save();
    res.status(200).json({});
  } catch (e) {
    console.error(e);
    res.status(401).json({ message: "Unauthenticated" });
  }
}
