import React, { Component } from 'react';
import { withStyles } from '@material-ui/core';

const styles = theme => ({
    root: {
      ...theme.mixins.gutters(),
      paddingTop: theme.spacing.unit * 2,
      paddingBottom: theme.spacing.unit * 2,
    },
    button: {
      margin: theme.spacing.unit,
    },
});

class Course extends Component {
    render(){return (<p></p>)}
}

export default withStyles(styles)(Course);