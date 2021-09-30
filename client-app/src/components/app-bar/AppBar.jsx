import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import { AppBar, Toolbar, Button, IconButton } from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';

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

  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <IconButton className={classes.menuButton} color="inherit" aria-label="Menu">
            <MenuIcon />
          </IconButton>
            {
              localStorage.getItem('user') ? <Button color="inherit">Logout</Button> : null
            }
        </Toolbar>
      </AppBar>
    </div>
  );
}

export default withStyles(styles)(ButtonAppBar);