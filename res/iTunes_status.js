var iTunes = WScript.CreateObject('iTunes.Application');
iTunes.soundVolume = 0;

try
{
	var state = iTunes.playerState == 1 ? 'playing' : 'paused';
	var title = iTunes.currentTrack.name;
	var artist = iTunes.currentTrack.artist;
	var location = iTunes.currentTrack.location.replace(/\\/g, '\\\\');
	var track = iTunes.currentTrack.databaseID || location;
	var pos = iTunes.playerPosition;
}
catch(x)
{
	var state = 'stopped';
	var title = '';
	var artist = '';
	var location = null;
	var track = null;
	var pos = -1;
}

var json = '{ state: "' + state + '", track: "' + track + '", title: "' + title + '", artist: "' + artist + '", url: "' + location + '", pos: "' + pos +'" }';
WScript.StdOut.Write(json);