import com.shoesclick.pipeline.languages.LanguageTools
import com.shoesclick.pipeline.languages.NpmTools
import com.shoesclick.pipeline.languages.MavenTools
import com.shoesclick.pipeline.languages.PythonTools
import com.shoesclick.pipeline.languages.GoTools
import com.shoesclick.pipeline.steps.GitHubTools
import com.shoesclick.pipeline.steps.DockerTools
import com.shoesclick.pipeline.steps.SonarTools
import com.shoesclick.pipeline.steps.KubernatesTools
import com.shoesclick.pipeline.model.Parameters

def call(body) {

    def params = [:]

    def gitHubTools = new GitHubTools(this)
    def dockerTools = new DockerTools(this)
    def sonarTools = new SonarTools(this)
    def kubernatesTools = new KubernatesTools(this)

    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    if (!params.package_manager) {
        currentBuild.result = 'FAILURE'
        error('deliveryPipeline => Missing required param: package_manager\n')
    }

    node {

        gitHubTools.checkoutSCM()

        def parameterModel = new Parameters(
                env.SONAR_SERVER_URL,
                env.SONAR_SCANNER_PATH,
                'sqp_5a70f9ae87771a7fe959d1df084fb8e45a8b4b68',
                env.JOB_NAME,
                env.WORKSPACE,
                env.DCK_ACCOUNT_ID,
                env.DCK_REPOSITORY,
                gitHubTools.getRevision()
        )       

        switch (params.package_panager){
            case 'npm':
                def npmTools = new NpmTools(this)
                npmTools.install()
                npmTools.test()
                break

            case 'maven':
                def mavenTools = new MavenTools(this)
                mavenTools.install()
                mavenTools.test()
                break

            case 'pip':
                def pythonTools = new PythonTools(this)
                pythonTools.install()
                pythonTools.test()
                break

            case 'go':
                def goTools = new GoTools(this)
                goTools.install()
                goTools.test()
                break

            default:
                currentBuild.result = 'FAILURE'
                error('deliveryPipeline => Invalid value for param: package_manager\nValue: ' + params.package_manager)

        }

       // sonarTools.scanProject(parameterModel)
        dockerTools.build(parameterModel)
        kubernatesTools.deployEKS(parameterModel)
        dockerTools.removeImagesDocker(parameterModel)

    }


}
