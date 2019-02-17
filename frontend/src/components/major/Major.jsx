import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';

const styles = theme => ({
    root: {
      ...theme.mixins.gutters(),
      paddingTop: theme.spacing.unit * 2,
      paddingBottom: theme.spacing.unit * 2,
    },
});


const Major = props => {
    const { classes, major } = props;
    return (
        <Paper className={classes.root} elevation={1}>
          <Typography variant="h5" component="h3">
            {major.title}
          </Typography>
          <Typography component="p">
            {major.description}
          </Typography>
        </Paper>
    )
}

export default withStyles(styles)(Major);