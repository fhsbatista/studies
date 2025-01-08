import {
  Button,
  Card,
  CardContent,
  CardHeader,
  Typography,
} from "@mui/material";
import axios from "axios";
import { GetServerSideProps, GetStaticPaths, GetStaticProps } from "next";
import * as React from "react";
import { DataGrid, GridColDef, GridColTypeDef } from "@mui/x-data-grid";
import Link from "next/link";
import { Link as MuiLink } from "@mui/material";
import { OrderStatus, OrderStatusTranslate } from "../../utils/models";
import useSWR from "swr";
import { useRouter } from "next/router";

const fetcher = (url: string) =>
  axios
    .get(url, { headers: { "x-api-token": "w5mrhd4un1" } })
    .then((res) => res.data);

type Props = {};
const OrderShowPage = (props: any) => {
  const router = useRouter();
  const { id } = router.query;
  const { data, error } = useSWR(`http://localhost:3000/orders/${id}`, fetcher);

  return data ? (
    <div style={{ height: 400, width: "100%" }}>
      <CardHeader title="Ordem" subheader={data.id} />
      <CardContent>
        <Typography>R$ {data.amount}</Typography>
      </CardContent>
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
