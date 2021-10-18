function Read-Response
{
    [CmdletBinding()]
    param
    (
        $response,
        $headers,
        $status,
        $repeats
    )

    return [PSCustomObject]@{
        Status = $status
        ISBN = $response.ISBN
        Valid = $response.Valid
        Message = $response.message
    }
}

$baseURL = "http://localhost:7071/api"
$validateURL = "$($baseURL)/ValidateISBN"
$createURL = "$($baseURL)/CreateISBN"
$postURL = "$($baseURL)/PostISBN"

#Content Type Header
$jsonBody = 
@{
    "book"=
    @{
        "title"="Bibel";
        "author"="Kath. Kirche"
        "publisher"="Alle"
        "isbn"="3920609263"
    }
}

$postRequest = 
{
    $response = invoke-restmethod -Method Post -Uri "$($postURL)"  -Body $jsonBody -ResponseHeadersVariable "headers" -StatusCodeVariable "status" -SkipHttpErrorCheck
    Read-Response -response $response -status $status -headers $headers -ErrorAction Ignore
}

$getRequest = 
{
    $response = invoke-restmethod -Method Get -Uri "$($ValidateURL)?ISBN=3920609263" -ResponseHeadersVariable "headers" -StatusCodeVariable "status" -SkipHttpErrorCheck
    Read-Response -response $response -status $status -headers $headers -ErrorAction Ignore
}

for(int y = 0; y -le 10000; y++)
{
    Start-Job -ScriptBlock {$postRequest}
}

for(int x = 0; x -le 2; x++) 
{
    Start-Job -ScriptBlock {$getRequest}
}