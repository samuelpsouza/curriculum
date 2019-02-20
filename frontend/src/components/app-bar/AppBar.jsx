import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import { AppBar, Toolbar, Typography,
  Button, IconButton } from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';
import Login from '../user/Login';

const styles = theme => ({
  root: {
    width: '100%',
  },
  grow: {
    flexGrow: 1,
  },
  menuButton: {
    marginLeft: -12,
    marginRight: 20,
  },
  title: {
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block',
    },
  }
});

const ButtonAppBar = props => {
  const { classes } = props;
  let openLogin = false;

  const handleOpenLogin = () => {
    openLogin = true;
  }

  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <IconButton className={classes.menuButton} color="inherit" aria-label="Menu">
            <MenuIcon />
          </IconButton>
          <Typography color="inherit" className={classes.grow}>
            Cursos
          </Typography>
          <Button color="inherit" onClick={() => handleOpenLogin()}>Login</Button>
          <Button color="inherit">Logout</Button>
        </Toolbar>
        <Login openLogin={openLogin} />
      </AppBar>
    </div>
  );
}

export default withStyles(styles)(ButtonAppBar);