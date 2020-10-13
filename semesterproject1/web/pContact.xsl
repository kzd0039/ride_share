<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Contact</title>
                <meta charset="UTF-8"></meta>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></link>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            </head>
            
            <body>
                
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="./pCatalog">PicXe</a>
                        </div>
                        <ul class="nav navbar-nav">
                            <li class="active">
                                <li>
                                    <a href="./pContact.xml">Contact</a>
                                </li>
                            </li>
                        </ul>
                    </div>
                </nav>
                <h2>Contact Us</h2>
                <table class="table">
                    <thead class="thead-dark table-hover">
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <xsl:for-each select="contacts/contact">
                        <tr>
                            <td>
                                <xsl:value-of select="firstName"/> 
                                <xsl:value-of select="LastName"/> 
                            </td>
                            <td>
                                <xsl:value-of select="email"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>