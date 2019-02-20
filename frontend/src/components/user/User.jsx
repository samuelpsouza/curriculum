import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import { ExpansionPanel, ExpansionPanelSummary,
  ExpansionPanelDetails, Button, Typography, Divider, 
  ExpansionPanelActions } from '@material-ui/core';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

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

const User = props => {
    const { classes, user, handleRemove } = props;

    return (
        <ExpansionPanel>
            <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />}>
                <Typography variant="h5" component="h3">
                    {user.username}
                </Typography>
            </ExpansionPanelSummary>
            <ExpansionPanelDetails>
                <Typography component="p">
                    {user.roles}
                </Typography>
            </ExpansionPanelDetails>
            <Divider />
            <ExpansionPanelActions>
                <Button color="secondary" className={classes.button} onClick={() => handleRemove(user.id)}>
                    Remover
                </Button>
                <Button className={classes.button}>
                    Editar
                </Button>
            </ExpansionPanelActions>
        </ExpansionPanel>
    );  
}

export default withStyles(styles)(User);