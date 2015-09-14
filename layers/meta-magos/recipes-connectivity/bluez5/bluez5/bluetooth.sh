#!/bin/sh
### BEGIN INIT INFO
# Provides:		Bluetooth
# Required-Start:	udev
# Required-Stop:
# Default-Start:	S
# Default-Stop:
# Short-Description:	Bring up Bluetooth interface
### END INIT INFO

BTIF="hci0"
HCIATTACH_OPTS="-s 115200"
HCIATTACH_ARGS="/dev/ttymxc2 bcm2035"
test -f /etc/default/bluetooth && . /etc/default/bluetooth

test -d "/sys/class/bluetooth/$BTIF" && exit 0
test -x "/usr/bin/hciattach" || exit 0
test -x "/usr/bin/hciconfig" || exit 0
if /usr/bin/hciattach $HCIATTACH_OPTS $HCIATTACH_ARGS; then
	/usr/bin/hciconfig $BTIF up
fi

