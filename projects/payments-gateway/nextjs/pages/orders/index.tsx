import { Typography } from "@mui/material";
import axios from "axios";
import { GetServerSideProps } from "next";
import * as React from "react";
import { DataGrid, GridColDef, GridColTypeDef } from "@mui/x-data-grid";
import Link from "next/link";
import { Link as MuiLink } from "@mui/material";
import { OrderStatus, OrderStatusTranslate } from "../../utils/models";
import { withIronSessionSsr } from "iron-session/next";
import ironConfig from "../../utils/iron-config";

type Props = {};
const OrdersPage = (props: any) => {
  const columns: GridColDef[] = [
    {
      field: "id",
      headerName: "ID",
      width: 300,
      renderCell: (params) => {
        return (
          <Link href={`/orders/${params.value}`}>
            <MuiLink>{params.value}</MuiLink>
          </Link>
        );
      },
    },
    { field: "amount", headerName: "Valor", width: 110 },
    {
      field: "credit_card_number",
      headerName: "Número Cartão de crédito",
      width: 200,
    },
    {
      field: "credit_card_name",
      headerName: "Nome Cartão de crédito",
      width: 200,
    },
    {
      field: "status",
      headerName: "Status",
      width: 110,
      valueFormatter: (value: OrderStatus) => OrderStatusTranslate[value],
    },
  ];
  return (
    <div style={{ height: 400, width: "100%" }}>
      <Typography component="h1" variant="h4">
        Minhas ordens
      </Typography>
      <DataGrid
        columns={columns}
        rows={props.data}
        checkboxSelection
        disableRowSelectionOnClick
      />
    </div>
  );
};

export default OrdersPage;

export const getServerSideProps: GetServerSideProps = withIronSessionSsr(async (context) => {
  const account = context.req.session.account;

  if (!account) {
    return {
      redirect: {
        destination: "/login",
        permanent: false,
      }
    }
  }
  const { data } = await axios.get("http://localhost:3001/api/orders", {
    headers: { cookie: context.req.headers.cookie as string },
  });

  return {
    props: {
      data: data,
    },
  };
}, ironConfig);
