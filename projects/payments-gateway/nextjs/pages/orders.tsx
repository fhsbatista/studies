// @flow
import { Button } from "@mui/material";
import axios from "axios";
import { GetServerSideProps } from "next";
import * as React from "react";
type Props = {};
const OrdersPage = (props: any) => {
  console.log(props.data);
  return <div>
    Listagem de orders - {props.name}
    <Button variant='contained'>Contained</Button>
    </div>;
};

export default OrdersPage;

export const getServerSideProps: GetServerSideProps = async (context) => {
  const { data } = await axios.get("http://localhost:3000/orders", {
    headers: { "x-api-token": "w5mrhd4un1" },
  });

  return {
    props: {
      data: data,
    },
  };
};
