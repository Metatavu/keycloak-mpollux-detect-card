<#import "template.ftl" as layout>
<@layout.registrationLayout displayMessage=false; section>
   <#if section = "header">
       <script type="text/javascript">
           function testCardPresence() {
                console.log("Testing card presence");

                try {
                    fetch("https://127.0.0.1:53952/version")
                        .then(function (response) {
                            console.log("Response status: " + response.status);
                            return response.json();
                        })
                        .then(function (responseJson) {
                            console.log("Response JSON: " + JSON.stringify(responseJson));
                            if (responseJson.hashAlgorithms) {
                                console.log("Card is present");
                                document.getElementById('no-card-text').style.display = "hidden";
                                document.getElementById('kc-form-login').submit();
                            } else {
                                console.log("Card is not present");
                                document.getElementById('no-card-text').style.display = "block";
                            }
                        })

                    if (response.status == 200) {
                        console.log("Card is present");
                        document.getElementById('kc-form-login').submit();
                    }
                } catch (e) {
                    // TODO: Failed to test for card presence
                }
           }

           setInterval(testCardPresence, ${checkInterval});
           testCardPresence();
       </script>
   <#elseif section = "form">
        <div id="kc-form">
            <div id="kc-form-wrapper">
                <#if realm.password>
                    <form id="kc-form-login" onsubmit="login.disabled = true; return true;" action="${url.loginAction}" method="post">
                        <p id="no-card-text" style="display: none">Could not find FineId card, please insert your card.</p>
                    </form>
                </#if>
            </div>
        </div>
    </#if>
</@layout.registrationLayout>