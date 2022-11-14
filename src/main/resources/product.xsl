<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:template match="/">

        <html lang="he" encoding="UTF-8">
            <head>
                <style>
                    table {
                    font-family: arial, sans-serif;
                    border-collapse: collapse;
                    width:
                    100%;
                    }
                    td, th {
                    border: 1px solid #dddddd;
                    text-align: right;
                    padding: 8px;
                    }
                    tr:nth-child(even) {
                    background-color: #dddddd;
                    }
                </style>
            </head>
            <body>
                <h2 style="text-align:center">חדשות ynet</h2>
                <table>
                    <tr>
                        <th>לינק</th>
                        <th>כתבה</th>
                        <th>כותרת</th>

                        <!--                        <th>link</th>-->
                        <!--                        <th>pubDate</th>-->
                        <!--                        <th>guid</th>-->
                        <!--                        <th>tags</th>-->
                    </tr>
                    <xsl:for-each select="rss/channel/item">
                        <tr>
                            <td>
                                <xsl:value-of select="link" />
                            </td>
                            <td>
                                <xsl:variable name="apos" select='"&apos;"'/>

                                <img src="{translate(substring-before(substring-after(description, 'src='), 'alt='),$apos,'' )}" style="width: 120px; cursor: pointer; float: left"
                                     onclick="toggleSize(this)" alt="page image from manuscript"/>
                                <xsl:value-of select="substring-after(substring-after(description, 'div'), 'div>')" />



                            </td>
                            <td>
                                <xsl:value-of select="title" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>