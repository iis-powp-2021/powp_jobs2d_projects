package edu.kis.powp.jobs2d.visitor;

import edu.kis.powp.jobs2d.command.*;

public class MirrorFigureCommandVisitor implements TransformCommandVisitor{

    private final boolean mirrorHorizontal;
    private final boolean mirrorVertical;
    private int posX;
    private int posY;
    private DriverCommand command;

    public MirrorFigureCommandVisitor(boolean mirrorHorizontal, boolean mirrorVertical){
        this.mirrorHorizontal = mirrorHorizontal;
        this.mirrorVertical = mirrorVertical;
    }

    private void setMirrored(int x, int y){
        if (mirrorHorizontal) {
            posX = x * -1;
        }
        else{
            posX = x;
        }
        if (mirrorVertical) {
            posY = y * -1;
        }
        else{
            posY = y;
        }
    }

    @Override
    public void visit(SetPositionCommand command) {
        setMirrored(command.getPosX(), command.getPosY());
        this.command = new SetPositionCommand(posX, posY);
    }

    @Override
    public void visit(OperateToCommand command) {
        setMirrored(command.getPosX(), command.getPosY());
        this.command = new OperateToCommand(posX, posY);
    }

    @Override
    public void visit(ICompoundCommand commands) {
        this.command = this.getCommandList(commands);
    }

    @Override
    public DriverCommand getResult() {
        return this.command;
    }
}
