package wccli

object Main {
  def main(args: Array[String]):Unit = {
    //it's a relatively common pattern to have almost no logic in your main method
    // we'll just kick off a Cli here:
    val cli = new Cli()
    cli.menu()
  }
}