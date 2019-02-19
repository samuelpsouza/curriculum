import React, { Component } from 'react';
import { withStyles } from '@material-ui/core';
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

class Course extends Component {
    render(){
        const { classes, course } = this.props;
        return (
            <ExpansionPanel>
                <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />}>
                    <Typography variant="h5" component="h3">
                    {course.code}
                    </Typography>
                </ExpansionPanelSummary>
                <ExpansionPanelDetails>
                    <Typography component="p">
                    {course.description}
                    </Typography>
                </ExpansionPanelDetails>
                <Divider />
                <ExpansionPanelActions>
                    <Button color="primary" className={classes.button} onClick={() => this.handleClickOpen()}>
                        Visualizar
                    </Button>
                </ExpansionPanelActions>
            </ExpansionPanel>
        )
    }
}

export default withStyles(styles)(Course);