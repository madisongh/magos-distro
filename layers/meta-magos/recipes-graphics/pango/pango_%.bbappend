pango_postinst() {
if ! [ -e $D${sysconfdir}/pango ] ; then
	mkdir -p $D${sysconfdir}/pango
fi

if [ -n "$D" ]; then
        exit 1
else
	${bindir}/${MLPREFIX}pango-querymodules > /etc/pango/${MLPREFIX}pango.modules
fi
}
