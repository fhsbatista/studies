import { AppBar, Toolbar, Typography } from "@mui/material";
import PaidIcon from '@mui/icons-material/Paid';

export const Navbar = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <PaidIcon />
        <Typography
          variant="h6"
          component="h1"
          sx={{ flexGrow: 1 }}
        >
          Payments Gateway
        </Typography>
      </Toolbar>
    </AppBar>
  );
};
