import {
  Box,
  Card,
  CardContent,
  CardHeader,
  Grid2,
  Typography,
} from "@mui/material";
import axios from "axios";
import { GetStaticPaths, GetStaticProps } from "next";
import * as React from "react";
import useSWR from "swr";
import Router, { useRouter } from "next/router";

const fetcher = (url: string) => axios.get(url).then((res) => res.data);

type Props = {};
const OrderShowPage = (props: any) => {
  const router = useRouter();
  const { id } = router.query;
  const { data, error } = useSWR(
    `http://localhost:3001/api/orders/${id}`,
    fetcher,
    {
      onError: (error) => {
        console.log(error);
        const statusCode = error.response.status;
        if (statusCode === 401 || statusCode === 403) {
          Router.push("/login");
        }
      },
    }
  );


  return data ? (
    <div style={{ height: 400, width: "100%" }}>
      <Grid2 container>
        <Card>
          <CardHeader
            title="Order"
            subheader={data.id}
            titleTypographyProps={{ align: "center" }}
            subheaderTypographyProps={{
              align: "center",
            }}
            sx={{
              backgroundColor: (theme) => theme.palette.grey[700],
            }}
          />
          <CardContent>
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "baseline",
                mb: 2,
              }}
            >
              <Typography component="h2" variant="h3" color="text.primary">
                R$ {data.amount}
              </Typography>
            </Box>
            <ul style={{ listStyle: "none" }}>
              <Typography component="li" variant="subtitle1">
                {data.credit_card_number}
              </Typography>
              <Typography component="li" variant="subtitle1">
                {data.credit_card_name}
              </Typography>
            </ul>
          </CardContent>
        </Card>
      </Grid2>
    </div>
  ) : null;
};

export default OrderShowPage;

export const getStaticProps: GetStaticProps = async (context) => {
  return {
    props: {},
    revalidate: 20, //Static page will remaing for 20s before being generated again when requested.
  };
};

export const getStaticPaths: GetStaticPaths = async (context) => {
  return {
    paths: [],
    fallback: "blocking",
  };
};
