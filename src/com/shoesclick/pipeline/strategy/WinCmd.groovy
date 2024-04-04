package com.shoesclick.pipeline.strategy

class WinCmd implements SystemCmd {

    def steps

    WinCmd(steps) { this.steps = steps }

    def cmd(command) {
        steps.bat command
    }

    def steps(){
       return steps
    }
}
